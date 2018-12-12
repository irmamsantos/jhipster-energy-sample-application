import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyTimePeriodData } from 'app/shared/model/energy-time-period-data.model';
import { EnergyTimePeriodDataService } from './energy-time-period-data.service';
import { EnergyTimePeriodDataComponent } from './energy-time-period-data.component';
import { EnergyTimePeriodDataDetailComponent } from './energy-time-period-data-detail.component';
import { EnergyTimePeriodDataUpdateComponent } from './energy-time-period-data-update.component';
import { EnergyTimePeriodDataDeletePopupComponent } from './energy-time-period-data-delete-dialog.component';
import { IEnergyTimePeriodData } from 'app/shared/model/energy-time-period-data.model';

@Injectable({ providedIn: 'root' })
export class EnergyTimePeriodDataResolve implements Resolve<IEnergyTimePeriodData> {
    constructor(private service: EnergyTimePeriodDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyTimePeriodData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyTimePeriodData>) => response.ok),
                map((energyTimePeriodData: HttpResponse<EnergyTimePeriodData>) => energyTimePeriodData.body)
            );
        }
        return of(new EnergyTimePeriodData());
    }
}

export const energyTimePeriodDataRoute: Routes = [
    {
        path: 'energy-time-period-data',
        component: EnergyTimePeriodDataComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyTimePeriodData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-time-period-data/:id/view',
        component: EnergyTimePeriodDataDetailComponent,
        resolve: {
            energyTimePeriodData: EnergyTimePeriodDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyTimePeriodData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-time-period-data/new',
        component: EnergyTimePeriodDataUpdateComponent,
        resolve: {
            energyTimePeriodData: EnergyTimePeriodDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyTimePeriodData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-time-period-data/:id/edit',
        component: EnergyTimePeriodDataUpdateComponent,
        resolve: {
            energyTimePeriodData: EnergyTimePeriodDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyTimePeriodData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyTimePeriodDataPopupRoute: Routes = [
    {
        path: 'energy-time-period-data/:id/delete',
        component: EnergyTimePeriodDataDeletePopupComponent,
        resolve: {
            energyTimePeriodData: EnergyTimePeriodDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyTimePeriodData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
