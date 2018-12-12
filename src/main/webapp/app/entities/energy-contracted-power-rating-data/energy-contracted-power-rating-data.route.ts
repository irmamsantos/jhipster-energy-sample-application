import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyContractedPowerRatingData } from 'app/shared/model/energy-contracted-power-rating-data.model';
import { EnergyContractedPowerRatingDataService } from './energy-contracted-power-rating-data.service';
import { EnergyContractedPowerRatingDataComponent } from './energy-contracted-power-rating-data.component';
import { EnergyContractedPowerRatingDataDetailComponent } from './energy-contracted-power-rating-data-detail.component';
import { EnergyContractedPowerRatingDataUpdateComponent } from './energy-contracted-power-rating-data-update.component';
import { EnergyContractedPowerRatingDataDeletePopupComponent } from './energy-contracted-power-rating-data-delete-dialog.component';
import { IEnergyContractedPowerRatingData } from 'app/shared/model/energy-contracted-power-rating-data.model';

@Injectable({ providedIn: 'root' })
export class EnergyContractedPowerRatingDataResolve implements Resolve<IEnergyContractedPowerRatingData> {
    constructor(private service: EnergyContractedPowerRatingDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyContractedPowerRatingData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyContractedPowerRatingData>) => response.ok),
                map(
                    (energyContractedPowerRatingData: HttpResponse<EnergyContractedPowerRatingData>) => energyContractedPowerRatingData.body
                )
            );
        }
        return of(new EnergyContractedPowerRatingData());
    }
}

export const energyContractedPowerRatingDataRoute: Routes = [
    {
        path: 'energy-contracted-power-rating-data',
        component: EnergyContractedPowerRatingDataComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyContractedPowerRatingData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-contracted-power-rating-data/:id/view',
        component: EnergyContractedPowerRatingDataDetailComponent,
        resolve: {
            energyContractedPowerRatingData: EnergyContractedPowerRatingDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyContractedPowerRatingData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-contracted-power-rating-data/new',
        component: EnergyContractedPowerRatingDataUpdateComponent,
        resolve: {
            energyContractedPowerRatingData: EnergyContractedPowerRatingDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyContractedPowerRatingData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-contracted-power-rating-data/:id/edit',
        component: EnergyContractedPowerRatingDataUpdateComponent,
        resolve: {
            energyContractedPowerRatingData: EnergyContractedPowerRatingDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyContractedPowerRatingData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyContractedPowerRatingDataPopupRoute: Routes = [
    {
        path: 'energy-contracted-power-rating-data/:id/delete',
        component: EnergyContractedPowerRatingDataDeletePopupComponent,
        resolve: {
            energyContractedPowerRatingData: EnergyContractedPowerRatingDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyContractedPowerRatingData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
