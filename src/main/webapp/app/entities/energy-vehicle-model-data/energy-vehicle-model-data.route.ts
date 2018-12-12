import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';
import { EnergyVehicleModelDataService } from './energy-vehicle-model-data.service';
import { EnergyVehicleModelDataComponent } from './energy-vehicle-model-data.component';
import { EnergyVehicleModelDataDetailComponent } from './energy-vehicle-model-data-detail.component';
import { EnergyVehicleModelDataUpdateComponent } from './energy-vehicle-model-data-update.component';
import { EnergyVehicleModelDataDeletePopupComponent } from './energy-vehicle-model-data-delete-dialog.component';
import { IEnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';

@Injectable({ providedIn: 'root' })
export class EnergyVehicleModelDataResolve implements Resolve<IEnergyVehicleModelData> {
    constructor(private service: EnergyVehicleModelDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyVehicleModelData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyVehicleModelData>) => response.ok),
                map((energyVehicleModelData: HttpResponse<EnergyVehicleModelData>) => energyVehicleModelData.body)
            );
        }
        return of(new EnergyVehicleModelData());
    }
}

export const energyVehicleModelDataRoute: Routes = [
    {
        path: 'energy-vehicle-model-data',
        component: EnergyVehicleModelDataComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVehicleModelData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-vehicle-model-data/:id/view',
        component: EnergyVehicleModelDataDetailComponent,
        resolve: {
            energyVehicleModelData: EnergyVehicleModelDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVehicleModelData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-vehicle-model-data/new',
        component: EnergyVehicleModelDataUpdateComponent,
        resolve: {
            energyVehicleModelData: EnergyVehicleModelDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVehicleModelData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-vehicle-model-data/:id/edit',
        component: EnergyVehicleModelDataUpdateComponent,
        resolve: {
            energyVehicleModelData: EnergyVehicleModelDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVehicleModelData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyVehicleModelDataPopupRoute: Routes = [
    {
        path: 'energy-vehicle-model-data/:id/delete',
        component: EnergyVehicleModelDataDeletePopupComponent,
        resolve: {
            energyVehicleModelData: EnergyVehicleModelDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVehicleModelData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
