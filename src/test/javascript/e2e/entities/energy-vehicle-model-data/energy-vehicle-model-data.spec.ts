/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    EnergyVehicleModelDataComponentsPage,
    EnergyVehicleModelDataDeleteDialog,
    EnergyVehicleModelDataUpdatePage
} from './energy-vehicle-model-data.page-object';

const expect = chai.expect;

describe('EnergyVehicleModelData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyVehicleModelDataUpdatePage: EnergyVehicleModelDataUpdatePage;
    let energyVehicleModelDataComponentsPage: EnergyVehicleModelDataComponentsPage;
    let energyVehicleModelDataDeleteDialog: EnergyVehicleModelDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyVehicleModelData', async () => {
        await navBarPage.goToEntity('energy-vehicle-model-data');
        energyVehicleModelDataComponentsPage = new EnergyVehicleModelDataComponentsPage();
        expect(await energyVehicleModelDataComponentsPage.getTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyVehicleModelData.home.title'
        );
    });

    it('should load create EnergyVehicleModelData page', async () => {
        await energyVehicleModelDataComponentsPage.clickOnCreateButton();
        energyVehicleModelDataUpdatePage = new EnergyVehicleModelDataUpdatePage();
        expect(await energyVehicleModelDataUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyVehicleModelData.home.createOrEditLabel'
        );
        await energyVehicleModelDataUpdatePage.cancel();
    });

    it('should create and save EnergyVehicleModelData', async () => {
        const nbButtonsBeforeCreate = await energyVehicleModelDataComponentsPage.countDeleteButtons();

        await energyVehicleModelDataComponentsPage.clickOnCreateButton();
        await promise.all([
            energyVehicleModelDataUpdatePage.setDescriptionInput('description'),
            energyVehicleModelDataUpdatePage.energyVehicleBrandDataSelectLastOption(),
            energyVehicleModelDataUpdatePage.brandSelectLastOption()
        ]);
        expect(await energyVehicleModelDataUpdatePage.getDescriptionInput()).to.eq('description');
        await energyVehicleModelDataUpdatePage.save();
        expect(await energyVehicleModelDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyVehicleModelDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyVehicleModelData', async () => {
        const nbButtonsBeforeDelete = await energyVehicleModelDataComponentsPage.countDeleteButtons();
        await energyVehicleModelDataComponentsPage.clickOnLastDeleteButton();

        energyVehicleModelDataDeleteDialog = new EnergyVehicleModelDataDeleteDialog();
        expect(await energyVehicleModelDataDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyVehicleModelData.delete.question'
        );
        await energyVehicleModelDataDeleteDialog.clickOnConfirmButton();

        expect(await energyVehicleModelDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
