import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyVoltageDataComponent,
    EnergyVoltageDataDetailComponent,
    EnergyVoltageDataUpdateComponent,
    EnergyVoltageDataDeletePopupComponent,
    EnergyVoltageDataDeleteDialogComponent,
    energyVoltageDataRoute,
    energyVoltageDataPopupRoute
} from './';

const ENTITY_STATES = [...energyVoltageDataRoute, ...energyVoltageDataPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyVoltageDataComponent,
        EnergyVoltageDataDetailComponent,
        EnergyVoltageDataUpdateComponent,
        EnergyVoltageDataDeleteDialogComponent,
        EnergyVoltageDataDeletePopupComponent
    ],
    entryComponents: [
        EnergyVoltageDataComponent,
        EnergyVoltageDataUpdateComponent,
        EnergyVoltageDataDeleteDialogComponent,
        EnergyVoltageDataDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyVoltageDataModule {}
