import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';
import { Principal } from 'app/core';
import { EnergyVehicleBrandDataService } from './energy-vehicle-brand-data.service';

@Component({
    selector: 'jhi-energy-vehicle-brand-data',
    templateUrl: './energy-vehicle-brand-data.component.html'
})
export class EnergyVehicleBrandDataComponent implements OnInit, OnDestroy {
    energyVehicleBrandData: IEnergyVehicleBrandData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private energyVehicleBrandDataService: EnergyVehicleBrandDataService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.energyVehicleBrandDataService.query().subscribe(
            (res: HttpResponse<IEnergyVehicleBrandData[]>) => {
                this.energyVehicleBrandData = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnergyVehicleBrandData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnergyVehicleBrandData) {
        return item.id;
    }

    registerChangeInEnergyVehicleBrandData() {
        this.eventSubscriber = this.eventManager.subscribe('energyVehicleBrandDataListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
