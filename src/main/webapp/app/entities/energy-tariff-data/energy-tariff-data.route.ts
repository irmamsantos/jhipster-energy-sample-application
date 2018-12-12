import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyTariffData } from 'app/shared/model/energy-tariff-data.model';
import { EnergyTariffDataService } from './energy-tariff-data.service';
import { EnergyTariffDataComponent } from './energy-tariff-data.component';
import { EnergyTariffDataDetailComponent } from './energy-tariff-data-detail.component';
import { EnergyTariffDataUpdateComponent } from './energy-tariff-data-update.component';
import { EnergyTariffDataDeletePopupComponent } from './energy-tariff-data-delete-dialog.component';
import { IEnergyTariffData } from 'app/shared/model/energy-tariff-data.model';

@Injectable({ providedIn: 'root' })
export class EnergyTariffDataResolve implements Resolve<IEnergyTariffData> {
    constructor(private service: EnergyTariffDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyTariffData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyTariffData>) => response.ok),
                map((energyTariffData: HttpResponse<EnergyTariffData>) => energyTariffData.body)
            );
        }
        return of(new EnergyTariffData());
    }
}

export const energyTariffDataRoute: Routes = [
    {
        path: 'energy-tariff-data',
        component: EnergyTariffDataComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyTariffData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-tariff-data/:id/view',
        component: EnergyTariffDataDetailComponent,
        resolve: {
            energyTariffData: EnergyTariffDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyTariffData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-tariff-data/new',
        component: EnergyTariffDataUpdateComponent,
        resolve: {
            energyTariffData: EnergyTariffDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyTariffData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-tariff-data/:id/edit',
        component: EnergyTariffDataUpdateComponent,
        resolve: {
            energyTariffData: EnergyTariffDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyTariffData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyTariffDataPopupRoute: Routes = [
    {
        path: 'energy-tariff-data/:id/delete',
        component: EnergyTariffDataDeletePopupComponent,
        resolve: {
            energyTariffData: EnergyTariffDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyTariffData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
