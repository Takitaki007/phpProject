import { useState } from "react";
import SidebarComponent from "./components/SidebarComponent";
import TopNavbarComponent from "./components/TopNavbarComponent";
import DashboardComponent from "./components/DashboardComponent";
import LearningMaterialsComponent from "./components/LearningMaterialsComponent";
import AssignmentsComponent from "./components/AssignmentsComponent";
import CardComponent from "./components/CardComponent";
import AddNewProjectComponent from "./components/AddNewProjectComponent";

function App() {
  // State to manage the list of projects
  const [projects, setProjects] = useState([]);

  // State to manage the search query
  const [searchQuery, setSearchQuery] = useState("");

  // Function to add a new project
  const handleAddProject = (newProject) => {
    setProjects((prevProjects) => [...prevProjects, newProject]);
  };

  // Function to handle search input
  const handleSearch = (query) => {
    setSearchQuery(query);
  };

  // Filter projects based on the search query
  const filteredProjects = projects.filter((project) =>
    project.projectName.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div className="grid grid-cols-12 w-full h-screen bg-white">
      {/* Sidebar */}
      <div className="col-span-2">
        <SidebarComponent />
      </div>

      {/* Main Content */}
      <div className="col-span-10 bg-gray-100">
        <div className="m-5">
          {/* Pass the handleSearch function to TopNavbarComponent */}
          <TopNavbarComponent onSearch={handleSearch} />
        </div>

        <div className="grid grid-cols-12 px-5">
          {/* Left Section */}
          <div className="col-span-9">
            <DashboardComponent />
            <div className="flex w-full mt-8 justify-between">
              <AssignmentsComponent />
              <AddNewProjectComponent onAddProject={handleAddProject} />
            </div>

            {/* Cards Section */}
            <div className="mt-5">
              <div className="grid grid-cols-3 gap-4 overflow-y-auto max-h-[60vh] pr-2">
                {/* Render the filtered project cards dynamically */}
                {filteredProjects.map((project, index) => (
                  <CardComponent key={index} project={project} />
                ))}
              </div>
            </div>
          </div>

          {/* Right Section */}
          <div className="col-span-3 pl-8">
            <LearningMaterialsComponent />
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;