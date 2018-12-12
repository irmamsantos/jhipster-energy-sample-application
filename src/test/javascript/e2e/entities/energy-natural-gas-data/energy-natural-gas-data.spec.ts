/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    EnergyNaturalGasDataComponentsPage,
    EnergyNaturalGasDataDeleteDialog,
    EnergyNaturalGasDataUpdatePage
} from './energy-natural-gas-data.page-object';

const expect = chai.expect;

describe('EnergyNaturalGasData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyNaturalGasDataUpdatePage: EnergyNaturalGasDataUpdatePage;
    let energyNaturalGasDataComponentsPage: EnergyNaturalGasDataComponentsPage;
    let energyNaturalGasDataDeleteDialog: EnergyNaturalGasDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyNaturalGasData', async () => {
        await navBarPage.goToEntity('energy-natural-gas-data');
        energyNaturalGasDataComponentsPage = new EnergyNaturalGasDataComponentsPage();
        expect(await energyNaturalGasDataComponentsPage.getTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyNaturalGasData.home.title'
        );
    });

    it('should load create EnergyNaturalGasData page', async () => {
        await energyNaturalGasDataComponentsPage.clickOnCreateButton();
        energyNaturalGasDataUpdatePage = new EnergyNaturalGasDataUpdatePage();
        expect(await energyNaturalGasDataUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyNaturalGasData.home.createOrEditLabel'
        );
        await energyNaturalGasDataUpdatePage.cancel();
    });

    it('should create and save EnergyNaturalGasData', async () => {
        const nbButtonsBeforeCreate = await energyNaturalGasDataComponentsPage.countDeleteButtons();

        await energyNaturalGasDataComponentsPage.clickOnCreateButton();
        await promise.all([
            energyNaturalGasDataUpdatePage.setPressureInput('pressure'),
            energyNaturalGasDataUpdatePage.setEchelonInput('echelon'),
            energyNaturalGasDataUpdatePage.setPressureDescriptionInput('pressureDescription')
        ]);
        expect(await energyNaturalGasDataUpdatePage.getPressureInput()).to.eq('pressure');
        expect(await energyNaturalGasDataUpdatePage.getEchelonInput()).to.eq('echelon');
        expect(await energyNaturalGasDataUpdatePage.getPressureDescriptionInput()).to.eq('pressureDescription');
        await energyNaturalGasDataUpdatePage.save();
        expect(await energyNaturalGasDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyNaturalGasDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyNaturalGasData', async () => {
        const nbButtonsBeforeDelete = await energyNaturalGasDataComponentsPage.countDeleteButtons();
        await energyNaturalGasDataComponentsPage.clickOnLastDeleteButton();

        energyNaturalGasDataDeleteDialog = new EnergyNaturalGasDataDeleteDialog();
        expect(await energyNaturalGasDataDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyNaturalGasData.delete.question'
        );
        await energyNaturalGasDataDeleteDialog.clickOnConfirmButton();

        expect(await energyNaturalGasDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
