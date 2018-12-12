import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';
import { Principal } from 'app/core';
import { EnergyVehicleModelDataService } from './energy-vehicle-model-data.service';

@Component({
    selector: 'jhi-energy-vehicle-model-data',
    templateUrl: './energy-vehicle-model-data.component.html'
})
export class EnergyVehicleModelDataComponent implements OnInit, OnDestroy {
    energyVehicleModelData: IEnergyVehicleModelData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private energyVehicleModelDataService: EnergyVehicleModelDataService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.energyVehicleModelDataService.query().subscribe(
            (res: HttpResponse<IEnergyVehicleModelData[]>) => {
                this.energyVehicleModelData = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnergyVehicleModelData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnergyVehicleModelData) {
        return item.id;
    }

    registerChangeInEnergyVehicleModelData() {
        this.eventSubscriber = this.eventManager.subscribe('energyVehicleModelDataListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
