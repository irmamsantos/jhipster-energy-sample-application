/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    EnergyNeedNGRequestComponentsPage,
    EnergyNeedNGRequestDeleteDialog,
    EnergyNeedNGRequestUpdatePage
} from './energy-need-ng-request.page-object';

const expect = chai.expect;

describe('EnergyNeedNGRequest e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyNeedNGRequestUpdatePage: EnergyNeedNGRequestUpdatePage;
    let energyNeedNGRequestComponentsPage: EnergyNeedNGRequestComponentsPage;
    let energyNeedNGRequestDeleteDialog: EnergyNeedNGRequestDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyNeedNGRequests', async () => {
        await navBarPage.goToEntity('energy-need-ng-request');
        energyNeedNGRequestComponentsPage = new EnergyNeedNGRequestComponentsPage();
        expect(await energyNeedNGRequestComponentsPage.getTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyNeedNGRequest.home.title'
        );
    });

    it('should load create EnergyNeedNGRequest page', async () => {
        await energyNeedNGRequestComponentsPage.clickOnCreateButton();
        energyNeedNGRequestUpdatePage = new EnergyNeedNGRequestUpdatePage();
        expect(await energyNeedNGRequestUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyNeedNGRequest.home.createOrEditLabel'
        );
        await energyNeedNGRequestUpdatePage.cancel();
    });

    it('should create and save EnergyNeedNGRequests', async () => {
        const nbButtonsBeforeCreate = await energyNeedNGRequestComponentsPage.countDeleteButtons();

        await energyNeedNGRequestComponentsPage.clickOnCreateButton();
        await promise.all([
            energyNeedNGRequestUpdatePage.setEnergyTypeInput('energyType'),
            energyNeedNGRequestUpdatePage.setUpdateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            energyNeedNGRequestUpdatePage.userSelectLastOption()
        ]);
        expect(await energyNeedNGRequestUpdatePage.getEnergyTypeInput()).to.eq('energyType');
        expect(await energyNeedNGRequestUpdatePage.getUpdateDateInput()).to.contain('2001-01-01T02:30');
        await energyNeedNGRequestUpdatePage.save();
        expect(await energyNeedNGRequestUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyNeedNGRequestComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyNeedNGRequest', async () => {
        const nbButtonsBeforeDelete = await energyNeedNGRequestComponentsPage.countDeleteButtons();
        await energyNeedNGRequestComponentsPage.clickOnLastDeleteButton();

        energyNeedNGRequestDeleteDialog = new EnergyNeedNGRequestDeleteDialog();
        expect(await energyNeedNGRequestDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyNeedNGRequest.delete.question'
        );
        await energyNeedNGRequestDeleteDialog.clickOnConfirmButton();

        expect(await energyNeedNGRequestComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
