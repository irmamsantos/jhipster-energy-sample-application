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
    voltageSelect = element(by.id('field_voltage'));
    tariffSelect = element(by.id('field_tariff'));
    cycleSelect = element(by.id('field_cycle'));
    timePeriodSelect = element(by.id('field_timePeriod'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async voltageSelectLastOption() {
        await this.voltageSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async voltageSelectOption(option) {
        await this.voltageSelect.sendKeys(option);
    }

    getVoltageSelect(): ElementFinder {
        return this.voltageSelect;
    }

    async getVoltageSelectedOption() {
        return this.voltageSelect.element(by.css('option:checked')).getText();
    }

    async tariffSelectLastOption() {
        await this.tariffSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async tariffSelectOption(option) {
        await this.tariffSelect.sendKeys(option);
    }

    getTariffSelect(): ElementFinder {
        return this.tariffSelect;
    }

    async getTariffSelectedOption() {
        return this.tariffSelect.element(by.css('option:checked')).getText();
    }

    async cycleSelectLastOption() {
        await this.cycleSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async cycleSelectOption(option) {
        await this.cycleSelect.sendKeys(option);
    }

    getCycleSelect(): ElementFinder {
        return this.cycleSelect;
    }

    async getCycleSelectedOption() {
        return this.cycleSelect.element(by.css('option:checked')).getText();
    }

    async timePeriodSelectLastOption() {
        await this.timePeriodSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async timePeriodSelectOption(option) {
        await this.timePeriodSelect.sendKeys(option);
    }

    getTimePeriodSelect(): ElementFinder {
        return this.timePeriodSelect;
    }

    async getTimePeriodSelectedOption() {
        return this.timePeriodSelect.element(by.css('option:checked')).getText();
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
