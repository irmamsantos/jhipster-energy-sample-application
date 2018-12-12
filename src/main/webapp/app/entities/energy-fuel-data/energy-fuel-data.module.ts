import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyFuelDataComponent,
    EnergyFuelDataDetailComponent,
    EnergyFuelDataUpdateComponent,
    EnergyFuelDataDeletePopupComponent,
    EnergyFuelDataDeleteDialogComponent,
    energyFuelDataRoute,
    energyFuelDataPopupRoute
} from './';

const ENTITY_STATES = [...energyFuelDataRoute, ...energyFuelDataPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyFuelDataComponent,
        EnergyFuelDataDetailComponent,
        EnergyFuelDataUpdateComponent,
        EnergyFuelDataDeleteDialogComponent,
        EnergyFuelDataDeletePopupComponent
    ],
    entryComponents: [
        EnergyFuelDataComponent,
        EnergyFuelDataUpdateComponent,
        EnergyFuelDataDeleteDialogComponent,
        EnergyFuelDataDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyFuelDataModule {}
