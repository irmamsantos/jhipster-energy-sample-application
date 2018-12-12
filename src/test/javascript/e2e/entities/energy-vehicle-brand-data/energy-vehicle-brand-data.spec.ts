/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    EnergyVehicleBrandDataComponentsPage,
    EnergyVehicleBrandDataDeleteDialog,
    EnergyVehicleBrandDataUpdatePage
} from './energy-vehicle-brand-data.page-object';

const expect = chai.expect;

describe('EnergyVehicleBrandData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyVehicleBrandDataUpdatePage: EnergyVehicleBrandDataUpdatePage;
    let energyVehicleBrandDataComponentsPage: EnergyVehicleBrandDataComponentsPage;
    let energyVehicleBrandDataDeleteDialog: EnergyVehicleBrandDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyVehicleBrandData', async () => {
        await navBarPage.goToEntity('energy-vehicle-brand-data');
        energyVehicleBrandDataComponentsPage = new EnergyVehicleBrandDataComponentsPage();
        expect(await energyVehicleBrandDataComponentsPage.getTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyVehicleBrandData.home.title'
        );
    });

    it('should load create EnergyVehicleBrandData page', async () => {
        await energyVehicleBrandDataComponentsPage.clickOnCreateButton();
        energyVehicleBrandDataUpdatePage = new EnergyVehicleBrandDataUpdatePage();
        expect(await energyVehicleBrandDataUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyVehicleBrandData.home.createOrEditLabel'
        );
        await energyVehicleBrandDataUpdatePage.cancel();
    });

    it('should create and save EnergyVehicleBrandData', async () => {
        const nbButtonsBeforeCreate = await energyVehicleBrandDataComponentsPage.countDeleteButtons();

        await energyVehicleBrandDataComponentsPage.clickOnCreateButton();
        await promise.all([
            energyVehicleBrandDataUpdatePage.setDescriptionInput('description'),
            energyVehicleBrandDataUpdatePage.energyVehicleModelDataSelectLastOption()
        ]);
        expect(await energyVehicleBrandDataUpdatePage.getDescriptionInput()).to.eq('description');
        await energyVehicleBrandDataUpdatePage.save();
        expect(await energyVehicleBrandDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyVehicleBrandDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyVehicleBrandData', async () => {
        const nbButtonsBeforeDelete = await energyVehicleBrandDataComponentsPage.countDeleteButtons();
        await energyVehicleBrandDataComponentsPage.clickOnLastDeleteButton();

        energyVehicleBrandDataDeleteDialog = new EnergyVehicleBrandDataDeleteDialog();
        expect(await energyVehicleBrandDataDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyVehicleBrandData.delete.question'
        );
        await energyVehicleBrandDataDeleteDialog.clickOnConfirmButton();

        expect(await energyVehicleBrandDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
