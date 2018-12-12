import { element, by, ElementFinder } from 'protractor';

export class EnergyFuelDataComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-energy-fuel-data div table .btn-danger'));
    title = element.all(by.css('jhi-energy-fuel-data div h2#page-heading span')).first();

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

export class EnergyFuelDataUpdatePage {
    pageTitle = element(by.id('jhi-energy-fuel-data-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    typeInput = element(by.id('field_type'));
    productInput = element(by.id('field_product'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setTypeInput(type) {
        await this.typeInput.sendKeys(type);
    }

    async getTypeInput() {
        return this.typeInput.getAttribute('value');
    }

    async setProductInput(product) {
        await this.productInput.sendKeys(product);
    }

    async getProductInput() {
        return this.productInput.getAttribute('value');
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

export class EnergyFuelDataDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-energyFuelData-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-energyFuelData'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
