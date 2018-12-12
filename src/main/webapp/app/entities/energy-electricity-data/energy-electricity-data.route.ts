import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyElectricityData } from 'app/shared/model/energy-electricity-data.model';
import { EnergyElectricityDataService } from './energy-electricity-data.service';
import { EnergyElectricityDataComponent } from './energy-electricity-data.component';
import { EnergyElectricityDataDetailComponent } from './energy-electricity-data-detail.component';
import { EnergyElectricityDataUpdateComponent } from './energy-electricity-data-update.component';
import { EnergyElectricityDataDeletePopupComponent } from './energy-electricity-data-delete-dialog.component';
import { IEnergyElectricityData } from 'app/shared/model/energy-electricity-data.model';

@Injectable({ providedIn: 'root' })
export class EnergyElectricityDataResolve implements Resolve<IEnergyElectricityData> {
    constructor(private service: EnergyElectricityDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyElectricityData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyElectricityData>) => response.ok),
                map((energyElectricityData: HttpResponse<EnergyElectricityData>) => energyElectricityData.body)
            );
        }
        return of(new EnergyElectricityData());
    }
}

export const energyElectricityDataRoute: Routes = [
    {
        path: 'energy-electricity-data',
        component: EnergyElectricityDataComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyElectricityData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-electricity-data/:id/view',
        component: EnergyElectricityDataDetailComponent,
        resolve: {
            energyElectricityData: EnergyElectricityDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyElectricityData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-electricity-data/new',
        component: EnergyElectricityDataUpdateComponent,
        resolve: {
            energyElectricityData: EnergyElectricityDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyElectricityData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-electricity-data/:id/edit',
        component: EnergyElectricityDataUpdateComponent,
        resolve: {
            energyElectricityData: EnergyElectricityDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyElectricityData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyElectricityDataPopupRoute: Routes = [
    {
        path: 'energy-electricity-data/:id/delete',
        component: EnergyElectricityDataDeletePopupComponent,
        resolve: {
            energyElectricityData: EnergyElectricityDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyElectricityData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
