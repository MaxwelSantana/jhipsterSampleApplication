import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StatusContagem } from './status-contagem.model';
import { StatusContagemPopupService } from './status-contagem-popup.service';
import { StatusContagemService } from './status-contagem.service';

@Component({
    selector: 'jhi-status-contagem-delete-dialog',
    templateUrl: './status-contagem-delete-dialog.component.html'
})
export class StatusContagemDeleteDialogComponent {

    statusContagem: StatusContagem;

    constructor(
        private statusContagemService: StatusContagemService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.statusContagemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'statusContagemListModification',
                content: 'Deleted an statusContagem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-status-contagem-delete-popup',
    template: ''
})
export class StatusContagemDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private statusContagemPopupService: StatusContagemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.statusContagemPopupService
                .open(StatusContagemDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
