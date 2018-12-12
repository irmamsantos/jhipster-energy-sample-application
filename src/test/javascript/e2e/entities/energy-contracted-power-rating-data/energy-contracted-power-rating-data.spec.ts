/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    EnergyContractedPowerRatingDataComponentsPage,
    EnergyContractedPowerRatingDataDeleteDialog,
    EnergyContractedPowerRatingDataUpdatePage
} from './energy-contracted-power-rating-data.page-object';

const expect = chai.expect;

describe('EnergyContractedPowerRatingData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyContractedPowerRatingDataUpdatePage: EnergyContractedPowerRatingDataUpdatePage;
    let energyContractedPowerRatingDataComponentsPage: EnergyContractedPowerRatingDataComponentsPage;
    let energyContractedPowerRatingDataDeleteDialog: EnergyContractedPowerRatingDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyContractedPowerRatingData', async () => {
        await navBarPage.goToEntity('energy-contracted-power-rating-data');
        energyContractedPowerRatingDataComponentsPage = new EnergyContractedPowerRatingDataComponentsPage();
        expect(await energyContractedPowerRatingDataComponentsPage.getTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyContractedPowerRatingData.home.title'
        );
    });

    it('should load create EnergyContractedPowerRatingData page', async () => {
        await energyContractedPowerRatingDataComponentsPage.clickOnCreateButton();
        energyContractedPowerRatingDataUpdatePage = new EnergyContractedPowerRatingDataUpdatePage();
        expect(await energyContractedPowerRatingDataUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyContractedPowerRatingData.home.createOrEditLabel'
        );
        await energyContractedPowerRatingDataUpdatePage.cancel();
    });

    it('should create and save EnergyContractedPowerRatingData', async () => {
        const nbButtonsBeforeCreate = await energyContractedPowerRatingDataComponentsPage.countDeleteButtons();

        await energyContractedPowerRatingDataComponentsPage.clickOnCreateButton();
        await promise.all([energyContractedPowerRatingDataUpdatePage.setDescriptionInput('description')]);
        expect(await energyContractedPowerRatingDataUpdatePage.getDescriptionInput()).to.eq('description');
        await energyContractedPowerRatingDataUpdatePage.save();
        expect(await energyContractedPowerRatingDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyContractedPowerRatingDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyContractedPowerRatingData', async () => {
        const nbButtonsBeforeDelete = await energyContractedPowerRatingDataComponentsPage.countDeleteButtons();
        await energyContractedPowerRatingDataComponentsPage.clickOnLastDeleteButton();

        energyContractedPowerRatingDataDeleteDialog = new EnergyContractedPowerRatingDataDeleteDialog();
        expect(await energyContractedPowerRatingDataDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyContractedPowerRatingData.delete.question'
        );
        await energyContractedPowerRatingDataDeleteDialog.clickOnConfirmButton();

        expect(await energyContractedPowerRatingDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
