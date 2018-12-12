import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnergyFuelData } from 'app/shared/model/energy-fuel-data.model';
import { Principal } from 'app/core';
import { EnergyFuelDataService } from './energy-fuel-data.service';

@Component({
    selector: 'jhi-energy-fuel-data',
    templateUrl: './energy-fuel-data.component.html'
})
export class EnergyFuelDataComponent implements OnInit, OnDestroy {
    energyFuelData: IEnergyFuelData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private energyFuelDataService: EnergyFuelDataService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.energyFuelDataService.query().subscribe(
            (res: HttpResponse<IEnergyFuelData[]>) => {
                this.energyFuelData = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnergyFuelData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnergyFuelData) {
        return item.id;
    }

    registerChangeInEnergyFuelData() {
        this.eventSubscriber = this.eventManager.subscribe('energyFuelDataListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
