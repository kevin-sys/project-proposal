import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EstudianteService } from '../../services/estudiante.service';
import { Estudiante } from '../../models/estudiante.model';

@Component({
  selector: 'app-view-student',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-student.component.html',
  styleUrl: './view-student.component.css'
})
export class ViewStudentComponent {
  estudiantes?: Estudiante[];

  constructor(private service: EstudianteService) {}

  ngOnInit(): void {
    this.consult();
  }

  consult() {
    this.service.get().subscribe(data => {
      this.estudiantes = data;
      console.log(data);
    });
  }
}
