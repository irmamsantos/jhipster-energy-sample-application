import { element, by, ElementFinder } from 'protractor';

export class EnergyVehicleModelDataComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-energy-vehicle-model-data div table .btn-danger'));
    title = element.all(by.css('jhi-energy-vehicle-model-data div h2#page-heading span')).first();

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

export class EnergyVehicleModelDataUpdatePage {
    pageTitle = element(by.id('jhi-energy-vehicle-model-data-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    descriptionInput = element(by.id('field_description'));
    energyVehicleBrandDataSelect = element(by.id('field_energyVehicleBrandData'));
    brandSelect = element(by.id('field_brand'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setDescriptionInput(description) {
        await this.descriptionInput.sendKeys(description);
    }

    async getDescriptionInput() {
        return this.descriptionInput.getAttribute('value');
    }

    async energyVehicleBrandDataSelectLastOption() {
        await this.energyVehicleBrandDataSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async energyVehicleBrandDataSelectOption(option) {
        await this.energyVehicleBrandDataSelect.sendKeys(option);
    }

    getEnergyVehicleBrandDataSelect(): ElementFinder {
        return this.energyVehicleBrandDataSelect;
    }

    async getEnergyVehicleBrandDataSelectedOption() {
        return this.energyVehicleBrandDataSelect.element(by.css('option:checked')).getText();
    }

    async brandSelectLastOption() {
        await this.brandSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async brandSelectOption(option) {
        await this.brandSelect.sendKeys(option);
    }

    getBrandSelect(): ElementFinder {
        return this.brandSelect;
    }

    async getBrandSelectedOption() {
        return this.brandSelect.element(by.css('option:checked')).getText();
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

export class EnergyVehicleModelDataDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-energyVehicleModelData-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-energyVehicleModelData'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
