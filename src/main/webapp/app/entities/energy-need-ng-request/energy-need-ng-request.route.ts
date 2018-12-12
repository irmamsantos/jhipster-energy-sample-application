import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyNeedNGRequest } from 'app/shared/model/energy-need-ng-request.model';
import { EnergyNeedNGRequestService } from './energy-need-ng-request.service';
import { EnergyNeedNGRequestComponent } from './energy-need-ng-request.component';
import { EnergyNeedNGRequestDetailComponent } from './energy-need-ng-request-detail.component';
import { EnergyNeedNGRequestUpdateComponent } from './energy-need-ng-request-update.component';
import { EnergyNeedNGRequestDeletePopupComponent } from './energy-need-ng-request-delete-dialog.component';
import { IEnergyNeedNGRequest } from 'app/shared/model/energy-need-ng-request.model';

@Injectable({ providedIn: 'root' })
export class EnergyNeedNGRequestResolve implements Resolve<IEnergyNeedNGRequest> {
    constructor(private service: EnergyNeedNGRequestService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyNeedNGRequest> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyNeedNGRequest>) => response.ok),
                map((energyNeedNGRequest: HttpResponse<EnergyNeedNGRequest>) => energyNeedNGRequest.body)
            );
        }
        return of(new EnergyNeedNGRequest());
    }
}

export const energyNeedNGRequestRoute: Routes = [
    {
        path: 'energy-need-ng-request',
        component: EnergyNeedNGRequestComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNeedNGRequest.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-need-ng-request/:id/view',
        component: EnergyNeedNGRequestDetailComponent,
        resolve: {
            energyNeedNGRequest: EnergyNeedNGRequestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNeedNGRequest.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-need-ng-request/new',
        component: EnergyNeedNGRequestUpdateComponent,
        resolve: {
            energyNeedNGRequest: EnergyNeedNGRequestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNeedNGRequest.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-need-ng-request/:id/edit',
        component: EnergyNeedNGRequestUpdateComponent,
        resolve: {
            energyNeedNGRequest: EnergyNeedNGRequestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNeedNGRequest.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyNeedNGRequestPopupRoute: Routes = [
    {
        path: 'energy-need-ng-request/:id/delete',
        component: EnergyNeedNGRequestDeletePopupComponent,
        resolve: {
            energyNeedNGRequest: EnergyNeedNGRequestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNeedNGRequest.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
