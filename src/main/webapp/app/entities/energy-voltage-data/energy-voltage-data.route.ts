import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';
import { EnergyVoltageDataService } from './energy-voltage-data.service';
import { EnergyVoltageDataComponent } from './energy-voltage-data.component';
import { EnergyVoltageDataDetailComponent } from './energy-voltage-data-detail.component';
import { EnergyVoltageDataUpdateComponent } from './energy-voltage-data-update.component';
import { EnergyVoltageDataDeletePopupComponent } from './energy-voltage-data-delete-dialog.component';
import { IEnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';

@Injectable({ providedIn: 'root' })
export class EnergyVoltageDataResolve implements Resolve<IEnergyVoltageData> {
    constructor(private service: EnergyVoltageDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyVoltageData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyVoltageData>) => response.ok),
                map((energyVoltageData: HttpResponse<EnergyVoltageData>) => energyVoltageData.body)
            );
        }
        return of(new EnergyVoltageData());
    }
}

export const energyVoltageDataRoute: Routes = [
    {
        path: 'energy-voltage-data',
        component: EnergyVoltageDataComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVoltageData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-voltage-data/:id/view',
        component: EnergyVoltageDataDetailComponent,
        resolve: {
            energyVoltageData: EnergyVoltageDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVoltageData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-voltage-data/new',
        component: EnergyVoltageDataUpdateComponent,
        resolve: {
            energyVoltageData: EnergyVoltageDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVoltageData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-voltage-data/:id/edit',
        component: EnergyVoltageDataUpdateComponent,
        resolve: {
            energyVoltageData: EnergyVoltageDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVoltageData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyVoltageDataPopupRoute: Routes = [
    {
        path: 'energy-voltage-data/:id/delete',
        component: EnergyVoltageDataDeletePopupComponent,
        resolve: {
            energyVoltageData: EnergyVoltageDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyVoltageData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
