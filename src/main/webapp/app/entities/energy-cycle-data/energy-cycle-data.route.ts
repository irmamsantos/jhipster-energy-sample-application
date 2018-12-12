import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyCycleData } from 'app/shared/model/energy-cycle-data.model';
import { EnergyCycleDataService } from './energy-cycle-data.service';
import { EnergyCycleDataComponent } from './energy-cycle-data.component';
import { EnergyCycleDataDetailComponent } from './energy-cycle-data-detail.component';
import { EnergyCycleDataUpdateComponent } from './energy-cycle-data-update.component';
import { EnergyCycleDataDeletePopupComponent } from './energy-cycle-data-delete-dialog.component';
import { IEnergyCycleData } from 'app/shared/model/energy-cycle-data.model';

@Injectable({ providedIn: 'root' })
export class EnergyCycleDataResolve implements Resolve<IEnergyCycleData> {
    constructor(private service: EnergyCycleDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyCycleData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyCycleData>) => response.ok),
                map((energyCycleData: HttpResponse<EnergyCycleData>) => energyCycleData.body)
            );
        }
        return of(new EnergyCycleData());
    }
}

export const energyCycleDataRoute: Routes = [
    {
        path: 'energy-cycle-data',
        component: EnergyCycleDataComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyCycleData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-cycle-data/:id/view',
        component: EnergyCycleDataDetailComponent,
        resolve: {
            energyCycleData: EnergyCycleDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyCycleData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-cycle-data/new',
        component: EnergyCycleDataUpdateComponent,
        resolve: {
            energyCycleData: EnergyCycleDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyCycleData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-cycle-data/:id/edit',
        component: EnergyCycleDataUpdateComponent,
        resolve: {
            energyCycleData: EnergyCycleDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyCycleData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyCycleDataPopupRoute: Routes = [
    {
        path: 'energy-cycle-data/:id/delete',
        component: EnergyCycleDataDeletePopupComponent,
        resolve: {
            energyCycleData: EnergyCycleDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyCycleData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
