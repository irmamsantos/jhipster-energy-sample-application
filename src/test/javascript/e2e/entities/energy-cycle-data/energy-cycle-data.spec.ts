/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EnergyCycleDataComponentsPage, EnergyCycleDataDeleteDialog, EnergyCycleDataUpdatePage } from './energy-cycle-data.page-object';

const expect = chai.expect;

describe('EnergyCycleData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let energyCycleDataUpdatePage: EnergyCycleDataUpdatePage;
    let energyCycleDataComponentsPage: EnergyCycleDataComponentsPage;
    let energyCycleDataDeleteDialog: EnergyCycleDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnergyCycleData', async () => {
        await navBarPage.goToEntity('energy-cycle-data');
        energyCycleDataComponentsPage = new EnergyCycleDataComponentsPage();
        expect(await energyCycleDataComponentsPage.getTitle()).to.eq('jhipsterEnergySampleApplicationApp.energyCycleData.home.title');
    });

    it('should load create EnergyCycleData page', async () => {
        await energyCycleDataComponentsPage.clickOnCreateButton();
        energyCycleDataUpdatePage = new EnergyCycleDataUpdatePage();
        expect(await energyCycleDataUpdatePage.getPageTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyCycleData.home.createOrEditLabel'
        );
        await energyCycleDataUpdatePage.cancel();
    });

    it('should create and save EnergyCycleData', async () => {
        const nbButtonsBeforeCreate = await energyCycleDataComponentsPage.countDeleteButtons();

        await energyCycleDataComponentsPage.clickOnCreateButton();
        await promise.all([energyCycleDataUpdatePage.setDescriptionInput('description')]);
        expect(await energyCycleDataUpdatePage.getDescriptionInput()).to.eq('description');
        await energyCycleDataUpdatePage.save();
        expect(await energyCycleDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await energyCycleDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnergyCycleData', async () => {
        const nbButtonsBeforeDelete = await energyCycleDataComponentsPage.countDeleteButtons();
        await energyCycleDataComponentsPage.clickOnLastDeleteButton();

        energyCycleDataDeleteDialog = new EnergyCycleDataDeleteDialog();
        expect(await energyCycleDataDeleteDialog.getDialogTitle()).to.eq(
            'jhipsterEnergySampleApplicationApp.energyCycleData.delete.question'
        );
        await energyCycleDataDeleteDialog.clickOnConfirmButton();

        expect(await energyCycleDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
