import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyTimePeriodDataComponent,
    EnergyTimePeriodDataDetailComponent,
    EnergyTimePeriodDataUpdateComponent,
    EnergyTimePeriodDataDeletePopupComponent,
    EnergyTimePeriodDataDeleteDialogComponent,
    energyTimePeriodDataRoute,
    energyTimePeriodDataPopupRoute
} from './';

const ENTITY_STATES = [...energyTimePeriodDataRoute, ...energyTimePeriodDataPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyTimePeriodDataComponent,
        EnergyTimePeriodDataDetailComponent,
        EnergyTimePeriodDataUpdateComponent,
        EnergyTimePeriodDataDeleteDialogComponent,
        EnergyTimePeriodDataDeletePopupComponent
    ],
    entryComponents: [
        EnergyTimePeriodDataComponent,
        EnergyTimePeriodDataUpdateComponent,
        EnergyTimePeriodDataDeleteDialogComponent,
        EnergyTimePeriodDataDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyTimePeriodDataModule {}
