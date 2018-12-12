/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    EnergyVoltageDataComponentsPage,
    EnergyVoltageDataDeleteDialog,
    EnergyVoltageDataUpdatePage
} from './energy-voltage-data.page-object';

const expect = chai.expect;

describe('EnergyVoltageData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyVoltageDataUpdatePage: EnergyVoltageDataUpdatePage;
    let energyVoltageDataComponentsPage: EnergyVoltageDataComponentsPage;
    let energyVoltageDataDeleteDialog: EnergyVoltageDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyVoltageData', async () => {
        await navBarPage.goToEntity('energy-voltage-data');
        energyVoltageDataComponentsPage = new EnergyVoltageDataComponentsPage();
        expect(await energyVoltageDataComponentsPage.getTitle()).to.eq('jhipsterEnergySampleApplicationApp.energyVoltageData.home.title');
    });

    it('should load create EnergyVoltageData page', async () => {
        await energyVoltageDataComponentsPage.clickOnCreateButton();
        energyVoltageDataUpdatePage = new EnergyVoltageDataUpdatePage();
        expect(await energyVoltageDataUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyVoltageData.home.createOrEditLabel'
        );
        await energyVoltageDataUpdatePage.cancel();
    });

    it('should create and save EnergyVoltageData', async () => {
        const nbButtonsBeforeCreate = await energyVoltageDataComponentsPage.countDeleteButtons();

        await energyVoltageDataComponentsPage.clickOnCreateButton();
        await promise.all([energyVoltageDataUpdatePage.setDescriptionInput('description')]);
        expect(await energyVoltageDataUpdatePage.getDescriptionInput()).to.eq('description');
        await energyVoltageDataUpdatePage.save();
        expect(await energyVoltageDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyVoltageDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyVoltageData', async () => {
        const nbButtonsBeforeDelete = await energyVoltageDataComponentsPage.countDeleteButtons();
        await energyVoltageDataComponentsPage.clickOnLastDeleteButton();

        energyVoltageDataDeleteDialog = new EnergyVoltageDataDeleteDialog();
        expect(await energyVoltageDataDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyVoltageData.delete.question'
        );
        await energyVoltageDataDeleteDialog.clickOnConfirmButton();

        expect(await energyVoltageDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
