import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LogAlteracaoContagem } from './log-alteracao-contagem.model';
import { LogAlteracaoContagemPopupService } from './log-alteracao-contagem-popup.service';
import { LogAlteracaoContagemService } from './log-alteracao-contagem.service';

@Component({
    selector: 'jhi-log-alteracao-contagem-delete-dialog',
    templateUrl: './log-alteracao-contagem-delete-dialog.component.html'
})
export class LogAlteracaoContagemDeleteDialogComponent {

    logAlteracaoContagem: LogAlteracaoContagem;

    constructor(
        private logAlteracaoContagemService: LogAlteracaoContagemService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.logAlteracaoContagemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'logAlteracaoContagemListModification',
                content: 'Deleted an logAlteracaoContagem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-log-alteracao-contagem-delete-popup',
    template: ''
})
export class LogAlteracaoContagemDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private logAlteracaoContagemPopupService: LogAlteracaoContagemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.logAlteracaoContagemPopupService
                .open(LogAlteracaoContagemDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
