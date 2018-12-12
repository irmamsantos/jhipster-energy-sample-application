/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    EnergyNRStateHistoryComponentsPage,
    EnergyNRStateHistoryDeleteDialog,
    EnergyNRStateHistoryUpdatePage
} from './energy-nr-state-history.page-object';

const expect = chai.expect;

describe('EnergyNRStateHistory e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyNRStateHistoryUpdatePage: EnergyNRStateHistoryUpdatePage;
    let energyNRStateHistoryComponentsPage: EnergyNRStateHistoryComponentsPage;
    let energyNRStateHistoryDeleteDialog: EnergyNRStateHistoryDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyNRStateHistories', async () => {
        await navBarPage.goToEntity('energy-nr-state-history');
        energyNRStateHistoryComponentsPage = new EnergyNRStateHistoryComponentsPage();
        expect(await energyNRStateHistoryComponentsPage.getTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyNRStateHistory.home.title'
        );
    });

    it('should load create EnergyNRStateHistory page', async () => {
        await energyNRStateHistoryComponentsPage.clickOnCreateButton();
        energyNRStateHistoryUpdatePage = new EnergyNRStateHistoryUpdatePage();
        expect(await energyNRStateHistoryUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyNRStateHistory.home.createOrEditLabel'
        );
        await energyNRStateHistoryUpdatePage.cancel();
    });

    it('should create and save EnergyNRStateHistories', async () => {
        const nbButtonsBeforeCreate = await energyNRStateHistoryComponentsPage.countDeleteButtons();

        await energyNRStateHistoryComponentsPage.clickOnCreateButton();
        await promise.all([
            energyNRStateHistoryUpdatePage.setEnergyTypeInput('energyType'),
            energyNRStateHistoryUpdatePage.setJustificationInput('justification'),
            energyNRStateHistoryUpdatePage.setUpdateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            energyNRStateHistoryUpdatePage.setStateValueInput('stateValue'),
            energyNRStateHistoryUpdatePage.needNGRequestSelectLastOption(),
            energyNRStateHistoryUpdatePage.userSelectLastOption()
        ]);
        expect(await energyNRStateHistoryUpdatePage.getEnergyTypeInput()).to.eq('energyType');
        expect(await energyNRStateHistoryUpdatePage.getJustificationInput()).to.eq('justification');
        expect(await energyNRStateHistoryUpdatePage.getUpdateDateInput()).to.contain('2001-01-01T02:30');
        expect(await energyNRStateHistoryUpdatePage.getStateValueInput()).to.eq('stateValue');
        await energyNRStateHistoryUpdatePage.save();
        expect(await energyNRStateHistoryUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyNRStateHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyNRStateHistory', async () => {
        const nbButtonsBeforeDelete = await energyNRStateHistoryComponentsPage.countDeleteButtons();
        await energyNRStateHistoryComponentsPage.clickOnLastDeleteButton();

        energyNRStateHistoryDeleteDialog = new EnergyNRStateHistoryDeleteDialog();
        expect(await energyNRStateHistoryDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyNRStateHistory.delete.question'
        );
        await energyNRStateHistoryDeleteDialog.clickOnConfirmButton();

        expect(await energyNRStateHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
