import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Contagem } from './contagem.model';
import { ContagemPopupService } from './contagem-popup.service';
import { ContagemService } from './contagem.service';

@Component({
    selector: 'jhi-contagem-delete-dialog',
    templateUrl: './contagem-delete-dialog.component.html'
})
export class ContagemDeleteDialogComponent {

    contagem: Contagem;

    constructor(
        private contagemService: ContagemService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.contagemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'contagemListModification',
                content: 'Deleted an contagem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-contagem-delete-popup',
    template: ''
})
export class ContagemDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private contagemPopupService: ContagemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.contagemPopupService
                .open(ContagemDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
