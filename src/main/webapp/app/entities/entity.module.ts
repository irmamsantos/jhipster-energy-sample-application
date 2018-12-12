import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterEnergySampleApplicationEnergyContractedPowerRatingDataModule } from './energy-contracted-power-rating-data/energy-contracted-power-rating-data.module';
import { JhipsterEnergySampleApplicationEnergyCycleDataModule } from './energy-cycle-data/energy-cycle-data.module';
import { JhipsterEnergySampleApplicationEnergyVoltageDataModule } from './energy-voltage-data/energy-voltage-data.module';
import { JhipsterEnergySampleApplicationEnergyTariffDataModule } from './energy-tariff-data/energy-tariff-data.module';
import { JhipsterEnergySampleApplicationEnergyTimePeriodDataModule } from './energy-time-period-data/energy-time-period-data.module';
import { JhipsterEnergySampleApplicationEnergyElectricityDataModule } from './energy-electricity-data/energy-electricity-data.module';
import { JhipsterEnergySampleApplicationEnergyFuelDataModule } from './energy-fuel-data/energy-fuel-data.module';
import { JhipsterEnergySampleApplicationEnergyNaturalGasDataModule } from './energy-natural-gas-data/energy-natural-gas-data.module';
import { JhipsterEnergySampleApplicationEnergyVehicleBrandDataModule } from './energy-vehicle-brand-data/energy-vehicle-brand-data.module';
import { JhipsterEnergySampleApplicationEnergyVehicleModelDataModule } from './energy-vehicle-model-data/energy-vehicle-model-data.module';
import { JhipsterEnergySampleApplicationEnergyNRStateHistoryModule } from './energy-nr-state-history/energy-nr-state-history.module';
import { JhipsterEnergySampleApplicationUserSACModule } from './user-sac/user-sac.module';
import { JhipsterEnergySampleApplicationEnergyNeedNGRequestModule } from './energy-need-ng-request/energy-need-ng-request.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterEnergySampleApplicationEnergyContractedPowerRatingDataModule,
        JhipsterEnergySampleApplicationEnergyCycleDataModule,
        JhipsterEnergySampleApplicationEnergyVoltageDataModule,
        JhipsterEnergySampleApplicationEnergyTariffDataModule,
        JhipsterEnergySampleApplicationEnergyTimePeriodDataModule,
        JhipsterEnergySampleApplicationEnergyElectricityDataModule,
        JhipsterEnergySampleApplicationEnergyFuelDataModule,
        JhipsterEnergySampleApplicationEnergyNaturalGasDataModule,
        JhipsterEnergySampleApplicationEnergyVehicleBrandDataModule,
        JhipsterEnergySampleApplicationEnergyVehicleModelDataModule,
        JhipsterEnergySampleApplicationEnergyNRStateHistoryModule,
        JhipsterEnergySampleApplicationUserSACModule,
        JhipsterEnergySampleApplicationEnergyNeedNGRequestModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEntityModule {}
