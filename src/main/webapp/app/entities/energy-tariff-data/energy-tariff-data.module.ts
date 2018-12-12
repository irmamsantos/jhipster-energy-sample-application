import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyTariffDataComponent,
    EnergyTariffDataDetailComponent,
    EnergyTariffDataUpdateComponent,
    EnergyTariffDataDeletePopupComponent,
    EnergyTariffDataDeleteDialogComponent,
    energyTariffDataRoute,
    energyTariffDataPopupRoute
} from './';

const ENTITY_STATES = [...energyTariffDataRoute, ...energyTariffDataPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyTariffDataComponent,
        EnergyTariffDataDetailComponent,
        EnergyTariffDataUpdateComponent,
        EnergyTariffDataDeleteDialogComponent,
        EnergyTariffDataDeletePopupComponent
    ],
    entryComponents: [
        EnergyTariffDataComponent,
        EnergyTariffDataUpdateComponent,
        EnergyTariffDataDeleteDialogComponent,
        EnergyTariffDataDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyTariffDataModule {}
