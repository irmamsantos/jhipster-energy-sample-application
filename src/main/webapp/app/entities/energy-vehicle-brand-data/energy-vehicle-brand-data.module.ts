import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyVehicleBrandDataComponent,
    EnergyVehicleBrandDataDetailComponent,
    EnergyVehicleBrandDataUpdateComponent,
    EnergyVehicleBrandDataDeletePopupComponent,
    EnergyVehicleBrandDataDeleteDialogComponent,
    energyVehicleBrandDataRoute,
    energyVehicleBrandDataPopupRoute
} from './';

const ENTITY_STATES = [...energyVehicleBrandDataRoute, ...energyVehicleBrandDataPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyVehicleBrandDataComponent,
        EnergyVehicleBrandDataDetailComponent,
        EnergyVehicleBrandDataUpdateComponent,
        EnergyVehicleBrandDataDeleteDialogComponent,
        EnergyVehicleBrandDataDeletePopupComponent
    ],
    entryComponents: [
        EnergyVehicleBrandDataComponent,
        EnergyVehicleBrandDataUpdateComponent,
        EnergyVehicleBrandDataDeleteDialogComponent,
        EnergyVehicleBrandDataDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyVehicleBrandDataModule {}
