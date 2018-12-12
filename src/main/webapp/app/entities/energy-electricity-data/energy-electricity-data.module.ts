import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyElectricityDataComponent,
    EnergyElectricityDataDetailComponent,
    EnergyElectricityDataUpdateComponent,
    EnergyElectricityDataDeletePopupComponent,
    EnergyElectricityDataDeleteDialogComponent,
    energyElectricityDataRoute,
    energyElectricityDataPopupRoute
} from './';

const ENTITY_STATES = [...energyElectricityDataRoute, ...energyElectricityDataPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyElectricityDataComponent,
        EnergyElectricityDataDetailComponent,
        EnergyElectricityDataUpdateComponent,
        EnergyElectricityDataDeleteDialogComponent,
        EnergyElectricityDataDeletePopupComponent
    ],
    entryComponents: [
        EnergyElectricityDataComponent,
        EnergyElectricityDataUpdateComponent,
        EnergyElectricityDataDeleteDialogComponent,
        EnergyElectricityDataDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyElectricityDataModule {}
