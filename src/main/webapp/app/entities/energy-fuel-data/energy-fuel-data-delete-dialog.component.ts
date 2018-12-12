import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyFuelData } from 'app/shared/model/energy-fuel-data.model';
import { EnergyFuelDataService } from './energy-fuel-data.service';

@Component({
    selector: 'jhi-energy-fuel-data-delete-dialog',
    templateUrl: './energy-fuel-data-delete-dialog.component.html'
})
export class EnergyFuelDataDeleteDialogComponent {
    energyFuelData: IEnergyFuelData;

    constructor(
        private energyFuelDataService: EnergyFuelDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyFuelDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyFuelDataListModification',
                content: 'Deleted an energyFuelData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-fuel-data-delete-popup',
    template: ''
})
export class EnergyFuelDataDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyFuelData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyFuelDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyFuelData = energyFuelData;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
