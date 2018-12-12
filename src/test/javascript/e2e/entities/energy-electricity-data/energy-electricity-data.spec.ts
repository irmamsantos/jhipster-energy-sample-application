/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    EnergyElectricityDataComponentsPage,
    EnergyElectricityDataDeleteDialog,
    EnergyElectricityDataUpdatePage
} from './energy-electricity-data.page-object';

const expect = chai.expect;

describe('EnergyElectricityData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyElectricityDataUpdatePage: EnergyElectricityDataUpdatePage;
    let energyElectricityDataComponentsPage: EnergyElectricityDataComponentsPage;
    let energyElectricityDataDeleteDialog: EnergyElectricityDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyElectricityData', async () => {
        await navBarPage.goToEntity('energy-electricity-data');
        energyElectricityDataComponentsPage = new EnergyElectricityDataComponentsPage();
        expect(await energyElectricityDataComponentsPage.getTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyElectricityData.home.title'
        );
    });

    it('should load create EnergyElectricityData page', async () => {
        await energyElectricityDataComponentsPage.clickOnCreateButton();
        energyElectricityDataUpdatePage = new EnergyElectricityDataUpdatePage();
        expect(await energyElectricityDataUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyElectricityData.home.createOrEditLabel'
        );
        await energyElectricityDataUpdatePage.cancel();
    });

    it('should create and save EnergyElectricityData', async () => {
        const nbButtonsBeforeCreate = await energyElectricityDataComponentsPage.countDeleteButtons();

        await energyElectricityDataComponentsPage.clickOnCreateButton();
        await promise.all([
            energyElectricityDataUpdatePage.energyVoltageDataSelectLastOption(),
            energyElectricityDataUpdatePage.energyTariffDataSelectLastOption(),
            energyElectricityDataUpdatePage.energyCycleDataSelectLastOption(),
            energyElectricityDataUpdatePage.energyTimePeriodDataSelectLastOption()
        ]);
        await energyElectricityDataUpdatePage.save();
        expect(await energyElectricityDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyElectricityDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyElectricityData', async () => {
        const nbButtonsBeforeDelete = await energyElectricityDataComponentsPage.countDeleteButtons();
        await energyElectricityDataComponentsPage.clickOnLastDeleteButton();

        energyElectricityDataDeleteDialog = new EnergyElectricityDataDeleteDialog();
        expect(await energyElectricityDataDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyElectricityData.delete.question'
        );
        await energyElectricityDataDeleteDialog.clickOnConfirmButton();

        expect(await energyElectricityDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
