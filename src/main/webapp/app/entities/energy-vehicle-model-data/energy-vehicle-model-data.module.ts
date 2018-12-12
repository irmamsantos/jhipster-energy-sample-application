import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyVehicleModelDataComponent,
    EnergyVehicleModelDataDetailComponent,
    EnergyVehicleModelDataUpdateComponent,
    EnergyVehicleModelDataDeletePopupComponent,
    EnergyVehicleModelDataDeleteDialogComponent,
    energyVehicleModelDataRoute,
    energyVehicleModelDataPopupRoute
} from './';

const ENTITY_STATES = [...energyVehicleModelDataRoute, ...energyVehicleModelDataPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyVehicleModelDataComponent,
        EnergyVehicleModelDataDetailComponent,
        EnergyVehicleModelDataUpdateComponent,
        EnergyVehicleModelDataDeleteDialogComponent,
        EnergyVehicleModelDataDeletePopupComponent
    ],
    entryComponents: [
        EnergyVehicleModelDataComponent,
        EnergyVehicleModelDataUpdateComponent,
        EnergyVehicleModelDataDeleteDialogComponent,
        EnergyVehicleModelDataDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyVehicleModelDataModule {}
