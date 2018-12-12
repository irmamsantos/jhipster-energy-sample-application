import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    EnergyCycleDataComponent,
    EnergyCycleDataDetailComponent,
    EnergyCycleDataUpdateComponent,
    EnergyCycleDataDeletePopupComponent,
    EnergyCycleDataDeleteDialogComponent,
    energyCycleDataRoute,
    energyCycleDataPopupRoute
} from './';

const ENTITY_STATES = [...energyCycleDataRoute, ...energyCycleDataPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnergyCycleDataComponent,
        EnergyCycleDataDetailComponent,
        EnergyCycleDataUpdateComponent,
        EnergyCycleDataDeleteDialogComponent,
        EnergyCycleDataDeletePopupComponent
    ],
    entryComponents: [
        EnergyCycleDataComponent,
        EnergyCycleDataUpdateComponent,
        EnergyCycleDataDeleteDialogComponent,
        EnergyCycleDataDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationEnergyCycleDataModule {}
