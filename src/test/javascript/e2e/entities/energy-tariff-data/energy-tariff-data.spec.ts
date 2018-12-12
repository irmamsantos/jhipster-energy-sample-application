/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EnergyTariffDataComponentsPage, EnergyTariffDataDeleteDialog, EnergyTariffDataUpdatePage } from './energy-tariff-data.page-object';

const expect = chai.expect;

describe('EnergyTariffData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyTariffDataUpdatePage: EnergyTariffDataUpdatePage;
    let energyTariffDataComponentsPage: EnergyTariffDataComponentsPage;
    let energyTariffDataDeleteDialog: EnergyTariffDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyTariffData', async () => {
        await navBarPage.goToEntity('energy-tariff-data');
        energyTariffDataComponentsPage = new EnergyTariffDataComponentsPage();
        expect(await energyTariffDataComponentsPage.getTitle()).to.eq('jhipsterEnergySampleApplicationApp.energyTariffData.home.title');
    });

    it('should load create EnergyTariffData page', async () => {
        await energyTariffDataComponentsPage.clickOnCreateButton();
        energyTariffDataUpdatePage = new EnergyTariffDataUpdatePage();
        expect(await energyTariffDataUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyTariffData.home.createOrEditLabel'
        );
        await energyTariffDataUpdatePage.cancel();
    });

    it('should create and save EnergyTariffData', async () => {
        const nbButtonsBeforeCreate = await energyTariffDataComponentsPage.countDeleteButtons();

        await energyTariffDataComponentsPage.clickOnCreateButton();
        await promise.all([energyTariffDataUpdatePage.setDescriptionInput('description')]);
        expect(await energyTariffDataUpdatePage.getDescriptionInput()).to.eq('description');
        await energyTariffDataUpdatePage.save();
        expect(await energyTariffDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyTariffDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyTariffData', async () => {
        const nbButtonsBeforeDelete = await energyTariffDataComponentsPage.countDeleteButtons();
        await energyTariffDataComponentsPage.clickOnLastDeleteButton();

        energyTariffDataDeleteDialog = new EnergyTariffDataDeleteDialog();
        expect(await energyTariffDataDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyTariffData.delete.question'
        );
        await energyTariffDataDeleteDialog.clickOnConfirmButton();

        expect(await energyTariffDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
