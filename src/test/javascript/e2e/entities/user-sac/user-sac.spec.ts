/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UserSACComponentsPage, UserSACDeleteDialog, UserSACUpdatePage } from './user-sac.page-object';

const expect = chai.expect;

describe('UserSAC e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let userSACUpdatePage: UserSACUpdatePage;
    let userSACComponentsPage: UserSACComponentsPage;
    let userSACDeleteDialog: UserSACDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load UserSACS', async () => {
        await navBarPage.goToEntity('user-sac');
        userSACComponentsPage = new UserSACComponentsPage();
        expect(await userSACComponentsPage.getTitle()).to.eq('jhipsterEnergySampleApplicationApp.userSAC.home.title');
    });

    it('should load create UserSAC page', async () => {
        await userSACComponentsPage.clickOnCreateButton();
        userSACUpdatePage = new UserSACUpdatePage();
        expect(await userSACUpdatePage.getPageTitle()).to.eq('jhipsterEnergySampleApplicationApp.userSAC.home.createOrEditLabel');
        await userSACUpdatePage.cancel();
    });

    it('should create and save UserSACS', async () => {
        const nbButtonsBeforeCreate = await userSACComponentsPage.countDeleteButtons();

        await userSACComponentsPage.clickOnCreateButton();
        await promise.all([
            userSACUpdatePage.setUserSACIdInput('5'),
            userSACUpdatePage.setUserNameInput('userName'),
            userSACUpdatePage.setUserEmailInput('userEmail'),
            userSACUpdatePage.setUpdateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM')
        ]);
        expect(await userSACUpdatePage.getUserSACIdInput()).to.eq('5');
        expect(await userSACUpdatePage.getUserNameInput()).to.eq('userName');
        expect(await userSACUpdatePage.getUserEmailInput()).to.eq('userEmail');
        expect(await userSACUpdatePage.getUpdateDateInput()).to.contain('2001-01-01T02:30');
        await userSACUpdatePage.save();
        expect(await userSACUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await userSACComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last UserSAC', async () => {
        const nbButtonsBeforeDelete = await userSACComponentsPage.countDeleteButtons();
        await userSACComponentsPage.clickOnLastDeleteButton();

        userSACDeleteDialog = new UserSACDeleteDialog();
        expect(await userSACDeleteDialog.getDialogTitle()).to.eq('jhipsterEnergySampleApplicationApp.userSAC.delete.question');
        await userSACDeleteDialog.clickOnConfirmButton();

        expect(await userSACComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
