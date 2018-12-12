import { element, by, ElementFinder } from 'protractor';

export class EnergyNRStateHistoryComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-energy-nr-state-history div table .btn-danger'));
    title = element.all(by.css('jhi-energy-nr-state-history div h2#page-heading span')).first();

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

export class EnergyNRStateHistoryUpdatePage {
    pageTitle = element(by.id('jhi-energy-nr-state-history-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    energyTypeInput = element(by.id('field_energyType'));
    justificationInput = element(by.id('field_justification'));
    updateDateInput = element(by.id('field_updateDate'));
    stateValueInput = element(by.id('field_stateValue'));
    userSelect = element(by.id('field_user'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setEnergyTypeInput(energyType) {
        await this.energyTypeInput.sendKeys(energyType);
    }

    async getEnergyTypeInput() {
        return this.energyTypeInput.getAttribute('value');
    }

    async setJustificationInput(justification) {
        await this.justificationInput.sendKeys(justification);
    }

    async getJustificationInput() {
        return this.justificationInput.getAttribute('value');
    }

    async setUpdateDateInput(updateDate) {
        await this.updateDateInput.sendKeys(updateDate);
    }

    async getUpdateDateInput() {
        return this.updateDateInput.getAttribute('value');
    }

    async setStateValueInput(stateValue) {
        await this.stateValueInput.sendKeys(stateValue);
    }

    async getStateValueInput() {
        return this.stateValueInput.getAttribute('value');
    }

    async userSelectLastOption() {
        await this.userSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async userSelectOption(option) {
        await this.userSelect.sendKeys(option);
    }

    getUserSelect(): ElementFinder {
        return this.userSelect;
    }

    async getUserSelectedOption() {
        return this.userSelect.element(by.css('option:checked')).getText();
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

export class EnergyNRStateHistoryDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-energyNRStateHistory-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-energyNRStateHistory'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
