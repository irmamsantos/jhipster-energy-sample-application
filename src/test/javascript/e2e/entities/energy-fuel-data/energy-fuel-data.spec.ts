/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EnergyFuelDataComponentsPage, EnergyFuelDataDeleteDialog, EnergyFuelDataUpdatePage } from './energy-fuel-data.page-object';

const expect = chai.expect;

describe('EnergyFuelData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyFuelDataUpdatePage: EnergyFuelDataUpdatePage;
    let energyFuelDataComponentsPage: EnergyFuelDataComponentsPage;
    let energyFuelDataDeleteDialog: EnergyFuelDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyFuelData', async () => {
        await navBarPage.goToEntity('energy-fuel-data');
        energyFuelDataComponentsPage = new EnergyFuelDataComponentsPage();
        expect(await energyFuelDataComponentsPage.getTitle()).to.eq('jhipsterEnergySampleApplicationApp.energyFuelData.home.title');
    });

    it('should load create EnergyFuelData page', async () => {
        await energyFuelDataComponentsPage.clickOnCreateButton();
        energyFuelDataUpdatePage = new EnergyFuelDataUpdatePage();
        expect(await energyFuelDataUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyFuelData.home.createOrEditLabel'
        );
        await energyFuelDataUpdatePage.cancel();
    });

    it('should create and save EnergyFuelData', async () => {
        const nbButtonsBeforeCreate = await energyFuelDataComponentsPage.countDeleteButtons();

        await energyFuelDataComponentsPage.clickOnCreateButton();
        await promise.all([energyFuelDataUpdatePage.setTypeInput('type'), energyFuelDataUpdatePage.setProductInput('product')]);
        expect(await energyFuelDataUpdatePage.getTypeInput()).to.eq('type');
        expect(await energyFuelDataUpdatePage.getProductInput()).to.eq('product');
        await energyFuelDataUpdatePage.save();
        expect(await energyFuelDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyFuelDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyFuelData', async () => {
        const nbButtonsBeforeDelete = await energyFuelDataComponentsPage.countDeleteButtons();
        await energyFuelDataComponentsPage.clickOnLastDeleteButton();

        energyFuelDataDeleteDialog = new EnergyFuelDataDeleteDialog();
        expect(await energyFuelDataDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyFuelData.delete.question'
        );
        await energyFuelDataDeleteDialog.clickOnConfirmButton();

        expect(await energyFuelDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
