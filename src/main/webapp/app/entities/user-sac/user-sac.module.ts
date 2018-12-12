import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterEnergySampleApplicationSharedModule } from 'app/shared';
import {
    UserSACComponent,
    UserSACDetailComponent,
    UserSACUpdateComponent,
    UserSACDeletePopupComponent,
    UserSACDeleteDialogComponent,
    userSACRoute,
    userSACPopupRoute
} from './';

const ENTITY_STATES = [...userSACRoute, ...userSACPopupRoute];

@NgModule({
    imports: [JhipsterEnergySampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UserSACComponent,
        UserSACDetailComponent,
        UserSACUpdateComponent,
        UserSACDeleteDialogComponent,
        UserSACDeletePopupComponent
    ],
    entryComponents: [UserSACComponent, UserSACUpdateComponent, UserSACDeleteDialogComponent, UserSACDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEnergySampleApplicationUserSACModule {}
