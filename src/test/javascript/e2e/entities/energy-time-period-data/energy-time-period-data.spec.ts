/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    EnergyTimePeriodDataComponentsPage,
    EnergyTimePeriodDataDeleteDialog,
    EnergyTimePeriodDataUpdatePage
} from './energy-time-period-data.page-object';

const expect = chai.expect;

describe('EnergyTimePeriodData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyTimePeriodDataUpdatePage: EnergyTimePeriodDataUpdatePage;
    let energyTimePeriodDataComponentsPage: EnergyTimePeriodDataComponentsPage;
    let energyTimePeriodDataDeleteDialog: EnergyTimePeriodDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyTimePeriodData', async () => {
        await navBarPage.goToEntity('energy-time-period-data');
        energyTimePeriodDataComponentsPage = new EnergyTimePeriodDataComponentsPage();
        expect(await energyTimePeriodDataComponentsPage.getTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyTimePeriodData.home.title'
        );
    });

    it('should load create EnergyTimePeriodData page', async () => {
        await energyTimePeriodDataComponentsPage.clickOnCreateButton();
        energyTimePeriodDataUpdatePage = new EnergyTimePeriodDataUpdatePage();
        expect(await energyTimePeriodDataUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyTimePeriodData.home.createOrEditLabel'
        );
        await energyTimePeriodDataUpdatePage.cancel();
    });

    it('should create and save EnergyTimePeriodData', async () => {
        const nbButtonsBeforeCreate = await energyTimePeriodDataComponentsPage.countDeleteButtons();

        await energyTimePeriodDataComponentsPage.clickOnCreateButton();
        await promise.all([energyTimePeriodDataUpdatePage.setDescriptionInput('description')]);
        expect(await energyTimePeriodDataUpdatePage.getDescriptionInput()).to.eq('description');
        await energyTimePeriodDataUpdatePage.save();
        expect(await energyTimePeriodDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyTimePeriodDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyTimePeriodData', async () => {
        const nbButtonsBeforeDelete = await energyTimePeriodDataComponentsPage.countDeleteButtons();
        await energyTimePeriodDataComponentsPage.clickOnLastDeleteButton();

        energyTimePeriodDataDeleteDialog = new EnergyTimePeriodDataDeleteDialog();
        expect(await energyTimePeriodDataDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyTimePeriodData.delete.question'
        );
        await energyTimePeriodDataDeleteDialog.clickOnConfirmButton();

        expect(await energyTimePeriodDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
