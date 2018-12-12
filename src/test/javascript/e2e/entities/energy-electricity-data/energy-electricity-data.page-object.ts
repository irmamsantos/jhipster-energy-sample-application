import { element, by, ElementFinder } from 'protractor';

export class EnergyElectricityDataComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-energy-electricity-data div table .btn-danger'));
    title = element.all(by.css('jhi-energy-electricity-data div h2#page-heading span')).first();

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

export class EnergyElectricityDataUpdatePage {
    pageTitle = element(by.id('jhi-energy-electricity-data-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    energyVoltageDataSelect = element(by.id('field_energyVoltageData'));
    energyTariffDataSelect = element(by.id('field_energyTariffData'));
    energyCycleDataSelect = element(by.id('field_energyCycleData'));
    energyTimePeriodDataSelect = element(by.id('field_energyTimePeriodData'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async energyVoltageDataSelectLastOption() {
        await this.energyVoltageDataSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async energyVoltageDataSelectOption(option) {
        await this.energyVoltageDataSelect.sendKeys(option);
    }

    getEnergyVoltageDataSelect(): ElementFinder {
        return this.energyVoltageDataSelect;
    }

    async getEnergyVoltageDataSelectedOption() {
        return this.energyVoltageDataSelect.element(by.css('option:checked')).getText();
    }

    async energyTariffDataSelectLastOption() {
        await this.energyTariffDataSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async energyTariffDataSelectOption(option) {
        await this.energyTariffDataSelect.sendKeys(option);
    }

    getEnergyTariffDataSelect(): ElementFinder {
        return this.energyTariffDataSelect;
    }

    async getEnergyTariffDataSelectedOption() {
        return this.energyTariffDataSelect.element(by.css('option:checked')).getText();
    }

    async energyCycleDataSelectLastOption() {
        await this.energyCycleDataSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async energyCycleDataSelectOption(option) {
        await this.energyCycleDataSelect.sendKeys(option);
    }

    getEnergyCycleDataSelect(): ElementFinder {
        return this.energyCycleDataSelect;
    }

    async getEnergyCycleDataSelectedOption() {
        return this.energyCycleDataSelect.element(by.css('option:checked')).getText();
    }

    async energyTimePeriodDataSelectLastOption() {
        await this.energyTimePeriodDataSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async energyTimePeriodDataSelectOption(option) {
        await this.energyTimePeriodDataSelect.sendKeys(option);
    }

    getEnergyTimePeriodDataSelect(): ElementFinder {
        return this.energyTimePeriodDataSelect;
    }

    async getEnergyTimePeriodDataSelectedOption() {
        return this.energyTimePeriodDataSelect.element(by.css('option:checked')).getText();
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

export class EnergyElectricityDataDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-energyElectricityData-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-energyElectricityData'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
