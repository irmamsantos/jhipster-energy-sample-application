import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyNRStateHistoryComponent,
    EnergyNRStateHistoryDetailComponent,
    EnergyNRStateHistoryUpdateComponent,
    EnergyNRStateHistoryDeletePopupComponent,
    EnergyNRStateHistoryDeleteDialogComponent,
    energyNRStateHistoryRoute,
    energyNRStateHistoryPopupRoute
} from './';

const ENTITY_STATES = [...energyNRStateHistoryRoute, ...energyNRStateHistoryPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyNRStateHistoryComponent,
        EnergyNRStateHistoryDetailComponent,
        EnergyNRStateHistoryUpdateComponent,
        EnergyNRStateHistoryDeleteDialogComponent,
        EnergyNRStateHistoryDeletePopupComponent
    ],
    entryComponents: [
        EnergyNRStateHistoryComponent,
        EnergyNRStateHistoryUpdateComponent,
        EnergyNRStateHistoryDeleteDialogComponent,
        EnergyNRStateHistoryDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyNRStateHistoryModule {}
