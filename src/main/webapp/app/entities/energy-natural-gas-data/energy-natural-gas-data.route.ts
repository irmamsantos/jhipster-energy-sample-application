import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyNaturalGasData } from 'app/shared/model/energy-natural-gas-data.model';
import { EnergyNaturalGasDataService } from './energy-natural-gas-data.service';
import { EnergyNaturalGasDataComponent } from './energy-natural-gas-data.component';
import { EnergyNaturalGasDataDetailComponent } from './energy-natural-gas-data-detail.component';
import { EnergyNaturalGasDataUpdateComponent } from './energy-natural-gas-data-update.component';
import { EnergyNaturalGasDataDeletePopupComponent } from './energy-natural-gas-data-delete-dialog.component';
import { IEnergyNaturalGasData } from 'app/shared/model/energy-natural-gas-data.model';

@Injectable({ providedIn: 'root' })
export class EnergyNaturalGasDataResolve implements Resolve<IEnergyNaturalGasData> {
    constructor(private service: EnergyNaturalGasDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyNaturalGasData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyNaturalGasData>) => response.ok),
                map((energyNaturalGasData: HttpResponse<EnergyNaturalGasData>) => energyNaturalGasData.body)
            );
        }
        return of(new EnergyNaturalGasData());
    }
}

export const energyNaturalGasDataRoute: Routes = [
    {
        path: 'energy-natural-gas-data',
        component: EnergyNaturalGasDataComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNaturalGasData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-natural-gas-data/:id/view',
        component: EnergyNaturalGasDataDetailComponent,
        resolve: {
            energyNaturalGasData: EnergyNaturalGasDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNaturalGasData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-natural-gas-data/new',
        component: EnergyNaturalGasDataUpdateComponent,
        resolve: {
            energyNaturalGasData: EnergyNaturalGasDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNaturalGasData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-natural-gas-data/:id/edit',
        component: EnergyNaturalGasDataUpdateComponent,
        resolve: {
            energyNaturalGasData: EnergyNaturalGasDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNaturalGasData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyNaturalGasDataPopupRoute: Routes = [
    {
        path: 'energy-natural-gas-data/:id/delete',
        component: EnergyNaturalGasDataDeletePopupComponent,
        resolve: {
            energyNaturalGasData: EnergyNaturalGasDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNaturalGasData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
