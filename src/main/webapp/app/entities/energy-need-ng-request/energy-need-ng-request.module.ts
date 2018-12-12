import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyNeedNGRequestComponent,
    EnergyNeedNGRequestDetailComponent,
    EnergyNeedNGRequestUpdateComponent,
    EnergyNeedNGRequestDeletePopupComponent,
    EnergyNeedNGRequestDeleteDialogComponent,
    energyNeedNGRequestRoute,
    energyNeedNGRequestPopupRoute
} from './';

const ENTITY_STATES = [...energyNeedNGRequestRoute, ...energyNeedNGRequestPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyNeedNGRequestComponent,
        EnergyNeedNGRequestDetailComponent,
        EnergyNeedNGRequestUpdateComponent,
        EnergyNeedNGRequestDeleteDialogComponent,
        EnergyNeedNGRequestDeletePopupComponent
    ],
    entryComponents: [
        EnergyNeedNGRequestComponent,
        EnergyNeedNGRequestUpdateComponent,
        EnergyNeedNGRequestDeleteDialogComponent,
        EnergyNeedNGRequestDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyNeedNGRequestModule {}
