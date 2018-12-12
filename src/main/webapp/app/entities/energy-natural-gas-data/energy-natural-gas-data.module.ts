import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyNaturalGasDataComponent,
    EnergyNaturalGasDataDetailComponent,
    EnergyNaturalGasDataUpdateComponent,
    EnergyNaturalGasDataDeletePopupComponent,
    EnergyNaturalGasDataDeleteDialogComponent,
    energyNaturalGasDataRoute,
    energyNaturalGasDataPopupRoute
} from './';

const ENTITY_STATES = [...energyNaturalGasDataRoute, ...energyNaturalGasDataPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyNaturalGasDataComponent,
        EnergyNaturalGasDataDetailComponent,
        EnergyNaturalGasDataUpdateComponent,
        EnergyNaturalGasDataDeleteDialogComponent,
        EnergyNaturalGasDataDeletePopupComponent
    ],
    entryComponents: [
        EnergyNaturalGasDataComponent,
        EnergyNaturalGasDataUpdateComponent,
        EnergyNaturalGasDataDeleteDialogComponent,
        EnergyNaturalGasDataDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyNaturalGasDataModule {}
