import { element, by, ElementFinder } from 'protractor';

export class EnergyNaturalGasDataComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-energy-natural-gas-data div table .btn-danger'));
    title = element.all(by.css('jhi-energy-natural-gas-data div h2#page-heading span')).first();

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

export class EnergyNaturalGasDataUpdatePage {
    pageTitle = element(by.id('jhi-energy-natural-gas-data-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    pressureInput = element(by.id('field_pressure'));
    echelonInput = element(by.id('field_echelon'));
    pressureDescriptionInput = element(by.id('field_pressureDescription'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setPressureInput(pressure) {
        await this.pressureInput.sendKeys(pressure);
    }

    async getPressureInput() {
        return this.pressureInput.getAttribute('value');
    }

    async setEchelonInput(echelon) {
        await this.echelonInput.sendKeys(echelon);
    }

    async getEchelonInput() {
        return this.echelonInput.getAttribute('value');
    }

    async setPressureDescriptionInput(pressureDescription) {
        await this.pressureDescriptionInput.sendKeys(pressureDescription);
    }

    async getPressureDescriptionInput() {
        return this.pressureDescriptionInput.getAttribute('value');
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

export class EnergyNaturalGasDataDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-energyNaturalGasData-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-energyNaturalGasData'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
