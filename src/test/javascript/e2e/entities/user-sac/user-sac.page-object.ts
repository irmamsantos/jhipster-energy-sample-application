import { element, by, ElementFinder } from 'protractor';

export class UserSACComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-user-sac div table .btn-danger'));
    title = element.all(by.css('jhi-user-sac div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class UserSACUpdatePage {
    pageTitle = element(by.id('jhi-user-sac-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    userSACIdInput = element(by.id('field_userSACId'));
    userNameInput = element(by.id('field_userName'));
    userEmailInput = element(by.id('field_userEmail'));
    updateDateInput = element(by.id('field_updateDate'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setUserSACIdInput(userSACId) {
        await this.userSACIdInput.sendKeys(userSACId);
    }

    async getUserSACIdInput() {
        return this.userSACIdInput.getAttribute('value');
    }

    async setUserNameInput(userName) {
        await this.userNameInput.sendKeys(userName);
    }

    async getUserNameInput() {
        return this.userNameInput.getAttribute('value');
    }

    async setUserEmailInput(userEmail) {
        await this.userEmailInput.sendKeys(userEmail);
    }

    async getUserEmailInput() {
        return this.userEmailInput.getAttribute('value');
    }

    async setUpdateDateInput(updateDate) {
        await this.updateDateInput.sendKeys(updateDate);
    }

    async getUpdateDateInput() {
        return this.updateDateInput.getAttribute('value');
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class UserSACDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-userSAC-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-userSAC'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
