import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnergyElectricityData } from 'app/shared/model/energy-electricity-data.model';
import { Principal } from 'app/core';
import { EnergyElectricityDataService } from './energy-electricity-data.service';

@Component({
    selector: 'jhi-energy-electricity-data',
    templateUrl: './energy-electricity-data.component.html'
})
export class EnergyElectricityDataComponent implements OnInit, OnDestroy {
    energyElectricityData: IEnergyElectricityData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private energyElectricityDataService: EnergyElectricityDataService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.energyElectricityDataService.query().subscribe(
            (res: HttpResponse<IEnergyElectricityData[]>) => {
                this.energyElectricityData = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnergyElectricityData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnergyElectricityData) {
        return item.id;
    }

    registerChangeInEnergyElectricityData() {
        this.eventSubscriber = this.eventManager.subscribe('energyElectricityDataListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
