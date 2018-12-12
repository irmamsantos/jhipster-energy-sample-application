import { element, by, ElementFinder } from 'protractor';

export class EnergyVehicleBrandDataComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-energy-vehicle-brand-data div table .btn-danger'));
    title = element.all(by.css('jhi-energy-vehicle-brand-data div h2#page-heading span')).first();

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

export class EnergyVehicleBrandDataUpdatePage {
    pageTitle = element(by.id('jhi-energy-vehicle-brand-data-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    descriptionInput = element(by.id('field_description'));
    energyVehicleModelDataSelect = element(by.id('field_energyVehicleModelData'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setDescriptionInput(description) {
        await this.descriptionInput.sendKeys(description);
    }

    async getDescriptionInput() {
        return this.descriptionInput.getAttribute('value');
    }

    async energyVehicleModelDataSelectLastOption() {
        await this.energyVehicleModelDataSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async energyVehicleModelDataSelectOption(option) {
        await this.energyVehicleModelDataSelect.sendKeys(option);
    }

    getEnergyVehicleModelDataSelect(): ElementFinder {
        return this.energyVehicleModelDataSelect;
    }

    async getEnergyVehicleModelDataSelectedOption() {
        return this.energyVehicleModelDataSelect.element(by.css('option:checked')).getText();
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

export class EnergyVehicleBrandDataDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-energyVehicleBrandData-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-energyVehicleBrandData'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
