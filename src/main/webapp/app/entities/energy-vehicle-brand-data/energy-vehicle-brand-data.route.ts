import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';
import { EnergyVehicleBrandDataService } from './energy-vehicle-brand-data.service';
import { EnergyVehicleBrandDataComponent } from './energy-vehicle-brand-data.component';
import { EnergyVehicleBrandDataDetailComponent } from './energy-vehicle-brand-data-detail.component';
import { EnergyVehicleBrandDataUpdateComponent } from './energy-vehicle-brand-data-update.component';
import { EnergyVehicleBrandDataDeletePopupComponent } from './energy-vehicle-brand-data-delete-dialog.component';
import { IEnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';

@Injectable({ providedIn: 'root' })
export class EnergyVehicleBrandDataResolve implements Resolve<IEnergyVehicleBrandData> {
    constructor(private service: EnergyVehicleBrandDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyVehicleBrandData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyVehicleBrandData>) => response.ok),
                map((energyVehicleBrandData: HttpResponse<EnergyVehicleBrandData>) => energyVehicleBrandData.body)
            );
        }
        return of(new EnergyVehicleBrandData());
    }
}

export const energyVehicleBrandDataRoute: Routes = [
    {
        path: 'energy-vehicle-brand-data',
        component: EnergyVehicleBrandDataComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVehicleBrandData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-vehicle-brand-data/:id/view',
        component: EnergyVehicleBrandDataDetailComponent,
        resolve: {
            energyVehicleBrandData: EnergyVehicleBrandDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVehicleBrandData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-vehicle-brand-data/new',
        component: EnergyVehicleBrandDataUpdateComponent,
        resolve: {
            energyVehicleBrandData: EnergyVehicleBrandDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVehicleBrandData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-vehicle-brand-data/:id/edit',
        component: EnergyVehicleBrandDataUpdateComponent,
        resolve: {
            energyVehicleBrandData: EnergyVehicleBrandDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVehicleBrandData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyVehicleBrandDataPopupRoute: Routes = [
    {
        path: 'energy-vehicle-brand-data/:id/delete',
        component: EnergyVehicleBrandDataDeletePopupComponent,
        resolve: {
            energyVehicleBrandData: EnergyVehicleBrandDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVehicleBrandData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
