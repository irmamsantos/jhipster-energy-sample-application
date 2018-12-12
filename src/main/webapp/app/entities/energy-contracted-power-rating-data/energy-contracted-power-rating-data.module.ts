import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyContractedPowerRatingDataComponent,
    EnergyContractedPowerRatingDataDetailComponent,
    EnergyContractedPowerRatingDataUpdateComponent,
    EnergyContractedPowerRatingDataDeletePopupComponent,
    EnergyContractedPowerRatingDataDeleteDialogComponent,
    energyContractedPowerRatingDataRoute,
    energyContractedPowerRatingDataPopupRoute
} from './';

const ENTITY_STATES = [...energyContractedPowerRatingDataRoute, ...energyContractedPowerRatingDataPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyContractedPowerRatingDataComponent,
        EnergyContractedPowerRatingDataDetailComponent,
        EnergyContractedPowerRatingDataUpdateComponent,
        EnergyContractedPowerRatingDataDeleteDialogComponent,
        EnergyContractedPowerRatingDataDeletePopupComponent
    ],
    entryComponents: [
        EnergyContractedPowerRatingDataComponent,
        EnergyContractedPowerRatingDataUpdateComponent,
        EnergyContractedPowerRatingDataDeleteDialogComponent,
        EnergyContractedPowerRatingDataDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyContractedPowerRatingDataModule {}
