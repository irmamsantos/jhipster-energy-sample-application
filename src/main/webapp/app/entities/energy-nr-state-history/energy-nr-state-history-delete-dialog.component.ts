import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyNRStateHistory } from 'app/shared/model/energy-nr-state-history.model';
import { EnergyNRStateHistoryService } from './energy-nr-state-history.service';

@Component({
    selector: 'jhi-energy-nr-state-history-delete-dialog',
    templateUrl: './energy-nr-state-history-delete-dialog.component.html'
})
export class EnergyNRStateHistoryDeleteDialogComponent {
    energyNRStateHistory: IEnergyNRStateHistory;

    constructor(
        private energyNRStateHistoryService: EnergyNRStateHistoryService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyNRStateHistoryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyNRStateHistoryListModification',
                content: 'Deleted an energyNRStateHistory'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-nr-state-history-delete-popup',
    template: ''
})
export class EnergyNRStateHistoryDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyNRStateHistory }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyNRStateHistoryDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyNRStateHistory = energyNRStateHistory;
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
