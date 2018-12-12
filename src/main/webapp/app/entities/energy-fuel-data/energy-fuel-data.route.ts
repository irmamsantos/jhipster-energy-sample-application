import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyFuelData } from 'app/shared/model/energy-fuel-data.model';
import { EnergyFuelDataService } from './energy-fuel-data.service';
import { EnergyFuelDataComponent } from './energy-fuel-data.component';
import { EnergyFuelDataDetailComponent } from './energy-fuel-data-detail.component';
import { EnergyFuelDataUpdateComponent } from './energy-fuel-data-update.component';
import { EnergyFuelDataDeletePopupComponent } from './energy-fuel-data-delete-dialog.component';
import { IEnergyFuelData } from 'app/shared/model/energy-fuel-data.model';

@Injectable({ providedIn: 'root' })
export class EnergyFuelDataResolve implements Resolve<IEnergyFuelData> {
    constructor(private service: EnergyFuelDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyFuelData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyFuelData>) => response.ok),
                map((energyFuelData: HttpResponse<EnergyFuelData>) => energyFuelData.body)
            );
        }
        return of(new EnergyFuelData());
    }
}

export const energyFuelDataRoute: Routes = [
    {
        path: 'energy-fuel-data',
        component: EnergyFuelDataComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyFuelData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-fuel-data/:id/view',
        component: EnergyFuelDataDetailComponent,
        resolve: {
            energyFuelData: EnergyFuelDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyFuelData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-fuel-data/new',
        component: EnergyFuelDataUpdateComponent,
        resolve: {
            energyFuelData: EnergyFuelDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyFuelData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-fuel-data/:id/edit',
        component: EnergyFuelDataUpdateComponent,
        resolve: {
            energyFuelData: EnergyFuelDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyFuelData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyFuelDataPopupRoute: Routes = [
    {
        path: 'energy-fuel-data/:id/delete',
        component: EnergyFuelDataDeletePopupComponent,
        resolve: {
            energyFuelData: EnergyFuelDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyFuelData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
